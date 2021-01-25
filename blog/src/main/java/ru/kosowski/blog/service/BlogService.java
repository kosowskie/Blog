package ru.kosowski.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kosowski.blog.model.Blog;
import ru.kosowski.blog.repository.BlogRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepo blogRepo;

    @Autowired
    public BlogService(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
        blogRepo.save(new Blog("Gitara siema","sgfhdhsshgdgdsjjgsj"));
        blogRepo.save(new Blog("Gitara siema","sgfhdhsshgdgdsjjgsj"));
        blogRepo.save(new Blog("Gitara siema","sgfhdhsshgdgdsjjgsj"));
    }

    public List<Blog> getAllNotes() {
        return blogRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Blog getNoteById(Long id) {
        return blogRepo.findById(id).get();
    }

    public void saveNote(Blog note){
        blogRepo.save(note);
    }

    public void updateNote(Blog note) {
        Optional<Blog> noteToUpdate = blogRepo.findById(note.getId());
        if(!note.getTitle().isEmpty())
            noteToUpdate.get().setTitle(note.getTitle());
        if(!note.getContent().isEmpty())
            noteToUpdate.get().setContent(note.getContent());
        blogRepo.save(noteToUpdate.get());
    }

    public void deleteNote(Long id) {
        blogRepo.deleteById(id);
    }
}
