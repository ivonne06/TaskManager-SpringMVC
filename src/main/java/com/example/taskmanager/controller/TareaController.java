
package com.example.taskmanager.controller;

import com.example.taskmanager.model.Tarea;
import com.example.taskmanager.repository.TareaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // Listar tareas
    @GetMapping
    public String listarTareas(Model model) {
        model.addAttribute("tareas", tareaRepository.findAll());
        return "tareas/lista";
    }

    // Mostrar formulario de registro
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tareas/formulario";
    }

    // Guardar tarea
    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaRepository.save(tarea);
        return "redirect:/tareas";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarTarea(@PathVariable Long id, Model model) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "ID de tarea inválido: " + id));

        model.addAttribute("tarea", tarea);
        return "tareas/formulario";
    }

    // Eliminar tarea
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
        return "redirect:/tareas";
    }
}
