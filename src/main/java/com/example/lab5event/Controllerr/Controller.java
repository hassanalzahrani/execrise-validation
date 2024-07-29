package com.example.lab5event.Controllerr;

import com.example.lab5event.Apirespons.ApiResponse;
import com.example.lab5event.modell.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping({"/api/v1/event"})
@RestController
public class Controller {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(200).body(events);

    }

    @PostMapping("add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }
        events.add(event);
        return ResponseEntity.status(201).body(new ApiResponse("Event added successfully"));
    }


    @PutMapping("update")
    public ResponseEntity updateEvent(@PathVariable int index, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }


    @DeleteMapping({"/delete/{index}"})
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted successfully"));

    }

    @PatchMapping("/update capacity/{index}/{newCapacity}")
    public ResponseEntity updateCapacity(@PathVariable int index, @PathVariable int newCapacity,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Event event = events.get(index);
        event.setCapacity(newCapacity);
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("capacity updated successfully"));
    }

    @GetMapping("get/{id}")
    public ResponseEntity search(@PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return ResponseEntity.status(200).body(event);

            }

        }
        return ResponseEntity.status(404).body(new ApiResponse("event not found"));

    }
}


