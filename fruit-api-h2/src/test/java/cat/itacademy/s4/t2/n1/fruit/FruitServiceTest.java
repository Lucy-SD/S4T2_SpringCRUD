package cat.itacademy.s4.t2.n1.fruit;

import cat.itacademy.s4.t2.n1.fruit.exception.FruitAlreadyExistsException;
import cat.itacademy.s4.t2.n1.fruit.exception.FruitNotFoundException;
import cat.itacademy.s4.t2.n1.fruit.model.Fruit;
import cat.itacademy.s4.t2.n1.fruit.repository.FruitRepository;
import cat.itacademy.s4.t2.n1.fruit.service.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FruitServiceTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private FruitService fruitService;

    private UUID id;
    private Fruit notSavedFruit;
    private Fruit savedFruit;
    private List<Fruit> fruits;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        notSavedFruit = new Fruit(null, "Banana", 22.50);
        savedFruit = new Fruit(id, "Banana", 22.50);

        fruits = List.of(
                new Fruit(UUID.randomUUID(), "Frutilla", 33.33),
                new Fruit(UUID.randomUUID(), "Naranja", 18.71),
                savedFruit
        );
    }

    @Test
    void givenValidData_whenCreatingFruit_thenSuccess() {

        when(fruitRepository.existsByName("Banana")).thenReturn(false);
        when(fruitRepository.save(any(Fruit.class))).thenReturn(savedFruit);

        Fruit result = fruitService.createFruit(notSavedFruit);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Banana", result.getName());
        assertEquals(22.50, result.getWeight());

        verify(fruitRepository).existsByName("Banana");
        verify(fruitRepository).save((any(Fruit.class)));
    }

    @Test
    void givenExistingName_whenCreatingFruit_thenThrowException() {
        when(fruitRepository.existsByName("Banana")).thenReturn(true);

        FruitAlreadyExistsException e = assertThrows(FruitAlreadyExistsException.class, () -> {
            fruitService.createFruit(notSavedFruit);
        });

        assertTrue(e.getMessage().contains("La fruta con nombre: " + notSavedFruit.getName() + " ya existe."));
        verify(fruitRepository).existsByName("Banana");
        verify(fruitRepository, never()).save((any(Fruit.class)));
    }

    @Test
    void givenNegativeWeight_whenCreatingFruit_thenThrowException() {
        Fruit negativePriceFruit = new Fruit(null, "Banana", -7);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            fruitService.createFruit(negativePriceFruit);
        });

        assertTrue(e.getMessage().contains("Una Fruta no puede tener peso negativo."));
        verify(fruitRepository, never()).existsByName(any());
        verify(fruitRepository, never()).save((any(Fruit.class)));
    }

    @Test
    void getFruits_returnsListOfAllFruits() {
        when(fruitRepository.findAll()).thenReturn(fruits);

        List<Fruit> result = fruitService.getFruits();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Frutilla", result.get(0).getName());
        assertEquals(33.33, result.get(0).getWeight());
        assertEquals("Naranja", result.get(1).getName());
        assertEquals(18.71, result.get(1).getWeight());
        assertEquals("Banana", result.get(2).getName());
        assertEquals(22.5, result.get(2).getWeight());
        verify(fruitRepository).findAll();
    }

    @Test
    void getFruitById_returnsFruit_whenExists() {
        when(fruitRepository.findById(id)).thenReturn(Optional.of(savedFruit));

        Fruit result = fruitService.getFruitById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Banana", result.getName());
        assertEquals(22.50, result.getWeight());
        verify(fruitRepository).findById(id);
    }

    @Test
    void getFruitById_whenNotFound_throwsException() {
        UUID nonExistingId = UUID.randomUUID();
        when(fruitRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        FruitNotFoundException e = assertThrows(FruitNotFoundException.class, () ->
                fruitService.getFruitById(nonExistingId)
        );

        assertTrue(e.getMessage().contains("No se encontró la fruta con ID: " + nonExistingId + "."));
        verify(fruitRepository).findById(nonExistingId);
    }

    @Test
    void getFruitByName_returnsFruit_whenExists() {
        List<Fruit> expectedList = List.of(savedFruit);

        when(fruitRepository.findByName("Banana")).thenReturn(expectedList);

        List<Fruit> result = fruitService.getFruitByName("Banana");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Banana", result.get(0).getName());
        assertEquals(22.50, result.get(0).getWeight());
    }

    @Test
    void getFruitByName_whenNotFound_returnsListOfFruits() {

        when(fruitRepository.findByName("Pera")).thenReturn(fruits);

        List<Fruit> result = fruitService.getFruitByName("Pera");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Frutilla", result.get(0).getName());
        assertEquals(33.33, result.get(0).getWeight());
        assertEquals("Naranja", result.get(1).getName());
        assertEquals(18.71, result.get(1).getWeight());
        assertEquals("Banana", result.get(2).getName());
        assertEquals(22.5, result.get(2).getWeight());
        verify(fruitRepository).findByName("Pera");
    }

    @Test
    void givenExistingFruit_whenUpdatingFruit_thenSuccess() {
        Fruit fruitToUpdate = new Fruit(id, "Banana de Ecuador", 28.87);

        when(fruitRepository.findById(id)).thenReturn(Optional.of(savedFruit));
        when(fruitRepository.save(any(Fruit.class))).thenReturn(fruitToUpdate);

        Fruit result = fruitService.updateFruit(fruitToUpdate);

        assertNotNull(result);
        assertEquals("Banana de Ecuador", result.getName());
        assertEquals(28.87, result.getWeight());

        verify(fruitRepository).findById(id);
        verify(fruitRepository).save(any(Fruit.class));
    }

    @Test
    void givenNonExistingFruit_whenUpdatingFruit_thenThrowException() {
        UUID nonExistingId = UUID.randomUUID();
        Fruit fruitToUpdate = new Fruit(nonExistingId, "Banana de Ecuador", 28.87);

        when(fruitRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        FruitNotFoundException e = assertThrows(FruitNotFoundException.class, () -> {
            fruitService.updateFruit(fruitToUpdate);
        });

        assertTrue(e.getMessage().contains("No se encontró la fruta con ID: " + nonExistingId + "."));
        verify(fruitRepository).findById(nonExistingId);
        verify(fruitRepository, never()).save(any(Fruit.class));
    }

    @Test
    void givenExistingId_whenDeletingFruit_thenSuccess() {
        when(fruitRepository.findById(id)).thenReturn(Optional.of(savedFruit));
        doNothing().when(fruitRepository).deleteById(id);

        fruitService.deleteFruitById(id);

        verify(fruitRepository).findById(id);
        verify(fruitRepository).deleteById(id);
    }

    @Test
    void givenNonExistingId_whenDeletingFruit_thenThrowException() {
        UUID nonExistingId = UUID.randomUUID();
        when(fruitRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        FruitNotFoundException e = assertThrows(FruitNotFoundException.class, () -> {
            fruitService.deleteFruitById(nonExistingId);
        });

        assertTrue(e.getMessage().contains("No se encontró la fruta con ID: " + nonExistingId + "."));
        verify(fruitRepository).findById(nonExistingId);
        verify(fruitRepository, never()).deleteById(any(UUID.class));
    }
}
