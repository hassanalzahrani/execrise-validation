package com.example.lap5tracker.Controllerr;

import com.example.lap5tracker.Apirespons.ApiResponse;
import com.example.lap5tracker.Model.Tracker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping({"/api/v1/tracker"})
@RestController
public class Controller {

    ArrayList<Tracker> trackers = new ArrayList<>();

    @GetMapping("get")
    public ResponseEntity getproject() {
        return ResponseEntity.status(200).body(trackers);

    } @PostMapping("add")
    public ResponseEntity projectadd(@Valid @RequestBody Tracker tracker, Errors errors) {
        if (errors.hasErrors()) {
            String message = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        trackers.add(tracker);
        return ResponseEntity.status(200).body(new ApiResponse("project added successfully"));


    }


    @PutMapping("update/{index}")

        public ResponseEntity projectUpdate(@PathVariable int index, @Valid @RequestBody Tracker tracker, Errors errors) {
            if (errors.hasErrors()) {
                String message = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();

                return ResponseEntity.status(400).body(message);
            }
            trackers.set(index, tracker);
            return ResponseEntity.status(200).body(new ApiResponse("project updated successfully"));
        }

    @DeleteMapping({"/delete/{index}"})
    public ResponseEntity deleteProject(@PathVariable int index) {
        trackers.remove(index);
       return ResponseEntity.status(200).body(trackers);
    }

    @PutMapping({"/change/{index}"})
    public ResponseEntity change(@Valid @PathVariable int index,Errors error) {

if (error.hasErrors()) {
    String message = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
    return ResponseEntity.status(400).body(message);
}
        if ((trackers.get(index)).getStatus().equalsIgnoreCase("done")) {
            (trackers.get(index)).setStatus("not done");
        } else if ((trackers.get(index)).getStatus().equalsIgnoreCase(" not done")) {
            (trackers.get(index)).setStatus("done");
        }
       return ResponseEntity.status(200).body(trackers);

    }
    @GetMapping({"/search/{title}"})
    public ResponseEntity searchByTitle( @PathVariable String title) {
        for (int i = 0; i < trackers.size(); ++i) {
            if ((trackers.get(i)).getTitle().equalsIgnoreCase(title)) {

            return ResponseEntity.status(200).body(trackers);
            }

        }
        return ResponseEntity.status(200).body(trackers);


    }
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<ObjectError>> getProjectsByCompany(@Valid @PathVariable String companyName, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        List<Tracker> projectsByCompany = trackers.stream()
                .filter(model -> model.getCompanyName().equalsIgnoreCase(companyName))
                .toList();

        if (projectsByCompany.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

    return null;
    }
}
