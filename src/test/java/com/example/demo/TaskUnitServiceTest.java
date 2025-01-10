package com.example.demo;

import com.example.demo.entity.Task;
import com.example.demo.entity.Task.Status;
import com.example.demo.service.TaskService;
import com.example.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskUnitServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description 1", Status.PENDING));

        Pageable pageable = PageRequest.of(0, 10); 
        Page<Task> taskPage = new PageImpl<>(tasks, pageable, tasks.size());

        when(taskRepository.findAll(pageable)).thenReturn(taskPage);

        List<Task> result = taskService.getAllTasks(pageable); 
        assertEquals(1, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task("Task 1", "Description 1", Status.PENDING);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);
        assertTrue(result.isPresent());
        assertEquals("Task 1", result.get().getTitle());
    }

    @Test
    public void testSaveTask() {
        Task task = new Task("Task 1", "Description 1", Status.PENDING);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.saveTask(task);
        assertEquals("Task 1", result.getTitle());
    }


    @Test
public void testDeleteTask() {
    Task task = new Task("Task 1", "Description 1", Status.PENDING);
    when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

    doNothing().when(taskRepository).delete(any(Task.class));

    taskService.deleteTask(1L);

    verify(taskRepository, times(1)).delete(task);
}


    @Test
    public void testUpdateTask() {
        Task task = new Task("Task 1", "Description 1", Status.PENDING);
        Task updatedTask = new Task("Task 2", "Updated Description", Status.COMPLETED);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(1L, updatedTask);
        assertEquals("Task 2", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(Status.COMPLETED, result.getStatus());
    }
}
