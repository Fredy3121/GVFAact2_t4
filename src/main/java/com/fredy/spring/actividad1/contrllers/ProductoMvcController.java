package com.fredy.spring.actividad1.contrllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fredy.spring.actividad1.dto.ProductoDTO;

@Controller
@RequestMapping("/productos")
public class ProductoMvcController {

    @Value("${app.nombre-autor}")
    private String autor;

    @Value("${app.titulo}")
    private String tituloApp;

    // Lista en memoria simulada
    private static final List<ProductoDTO> catalogo = new ArrayList<>(List.of(
        new ProductoDTO(1L, "Cuaderno Profesional", 35.50, "Papeleria"),
        new ProductoDTO(2L, "Calculadora Cientifica", 250.00, "Electronica"),
        new ProductoDTO(3L, "Memoria USB 64GB", 180.00, "Electronica"),
        new ProductoDTO(4L, "Paquete Hojas Blancas", 120.00, "Papeleria")
    ));

    // a) Lista con Model, th:each y filtro opcional con @RequestParam
    @GetMapping("/lista")
    public String listarProductos(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        List<ProductoDTO> resultado = (categoria != null && !categoria.isBlank())
            ? catalogo.stream().filter(p -> p.getCategoria().equalsIgnoreCase(categoria)).collect(Collectors.toList())
            : catalogo;

        model.addAttribute("titulo", tituloApp);
        model.addAttribute("autor", autor);
        model.addAttribute("productos", resultado);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "productos/lista";
    }

    // Detalle por id con @PathVariable
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") Long id, Model model) {
        ProductoDTO producto = catalogo.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

        model.addAttribute("titulo", "Detalle de Producto");
        model.addAttribute("autor", autor);
        model.addAttribute("producto", producto);
        return "productos/detalle";
    }

    // b) Formulario para crear producto
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Producto");
        model.addAttribute("producto", new ProductoDTO());
        return "productos/formulario";
    }

    // Procesar formulario con @ModelAttribute
    @PostMapping("/guardar")
    public String procesarFormulario(@ModelAttribute("producto") ProductoDTO producto, Model model) {
        producto.setId((long) (catalogo.size() + 1));
        catalogo.add(producto);
        model.addAttribute("titulo", "Producto Registrado con Éxito");
        model.addAttribute("producto", producto);
        model.addAttribute("autor", autor);
        return "productos/resultado";
    }
}
