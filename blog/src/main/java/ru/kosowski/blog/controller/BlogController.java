package ru.kosowski.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kosowski.blog.model.Blog;
import ru.kosowski.blog.service.BlogService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class BlogController {
    private BlogService noteService;

    @Autowired
    public BlogController(BlogService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("note", new Blog());
        return "index";
    }

    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute @Valid Blog note, BindingResult bindingError) {
        if(bindingError.hasErrors()) {
            return "redirect:/";
        }
        noteService.saveNote(note);
        return "redirect:/";
    }
    @GetMapping("/updateNote/{id}")
    public String updateNote(@PathVariable Long id, Model model) {
        model.addAttribute("noteToUpdate", noteService.getNoteById(id));
        return "update";
    }

    @PostMapping("/updateNote/{id}")
    public String updatePost(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String content,
                             @PathVariable Long id) {
        Blog noteToUpdate = noteService.getNoteById(id);
        noteToUpdate.setTitle(title);
        noteToUpdate.setContent(content);
        noteService.updateNote(noteToUpdate);
        return "redirect:/";
    }
    @GetMapping("/deleteNote/{id}")
    public String deletNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}
