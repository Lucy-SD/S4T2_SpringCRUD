package cat.itacademy.s4.t2.n2.fruit;

import cat.itacademy.s4.t2.n2.fruit.model.Fruit;
import cat.itacademy.s4.t2.n2.fruit.repository.JpaFruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaFruitRepository fruitRepository;

    @BeforeEach
    void setUp() {
        fruitRepository.deleteAll();
    }

    @Test
    void createFruit_returnsFruitWithId() {
        String jsonFruit = "{\"name\":\"Banana\",\"weight\":22.22}";

        assertThatNoException().isThrownBy(() ->

                mockMvc.perform(post("/fruits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonFruit))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name", is("Banana")))
                        .andExpect(jsonPath("$.weight", is(22.22)))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{\"name\":\"\",\"weight\":22.22}",
            "{\"weight\":22.22}",
            "{\"name\":\"   \",\"weight\":22.22}",
            "{\"name\":\"Banana\",\"weight\":-22.22}",
            "{\"name\":\"Banana\",\"weight\":0}"
    })
    void createFruit_returns400_whenNameInvalidInput(String jsonFruit) {

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(post("/fruits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonFruit))
                        .andExpect(status().isBadRequest())
        );
    }

    @Test
    void createFruit_returns400_whenNameAlreadyExists() {
        fruitRepository.save(new Fruit(null, "Banana", 22.22));

        String jsonFruit = "{\"name\":\"Banana\",\"weight\":15.51}";

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(post("/fruits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonFruit))
                        .andExpect(status().isBadRequest())
        );
    }

    @Test
    void getFruits_returnsEmptyList_whenNoFruits() {
        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(get("/fruits"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(0)))
        );
    }

    @Test
    void getFruits_returnsAllFruits() {
        fruitRepository.save(new Fruit(null, "Banana", 22.22));
        fruitRepository.save(new Fruit(null, "Anana", 18.91));

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(get("/fruits"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect(jsonPath("$[*].name", containsInAnyOrder("Banana", "Anana")))
                        .andExpect(jsonPath("$[*].weight", containsInAnyOrder(18.91, 22.22)))
        );
    }

    @Test
    void getFruitById_returnsCorrectFruit() {
        Fruit fruit = fruitRepository.save(new Fruit(null, "Banana", 22.22));

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(get("/fruits/{id}", fruit.getId()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(fruit.getId().intValue())))
                        .andExpect(jsonPath("$.name", is("Banana")))
                        .andExpect(jsonPath("$.weight", is(22.22)))
        );
    }

    @Test
    void getFruitById_returns404_whenFruitNotFound() {
        Long nonExistingId = 777L;

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(get("/fruits/{id}", nonExistingId))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    void updateFruit_returnsUpdatedFruit_whenExists() {
        Fruit savedFruit = fruitRepository.save(new Fruit(null, "Banana", 22.22));
        String updateJsonFruit = "{\"name\":\"Banana de Ecuador\",\"weight\":31.13}";

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(put("/fruits/{id}", savedFruit.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJsonFruit))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(savedFruit.getId().intValue())))
                        .andExpect(jsonPath("$.name", is("Banana de Ecuador")))
                        .andExpect(jsonPath("$.weight", is(31.13)))
        );
    }

    @Test
    void updateFruit_returns404_whenFruitNotFound() {
        Long nonExistingId = 777L;
        String updateJsonFruit = "{\"name\":\"Kiwi\",\"weight\":10.01}";

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(put("/fruits/{id}", nonExistingId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJsonFruit))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    void deleteFruitById_returns204_whenFruitExists() {
        Fruit fruit = fruitRepository.save(new Fruit(null, "Banana", 22.22));

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(delete("/fruits/{id}", fruit.getId()))
                        .andExpect(status().isNoContent())
        );
    }

    @Test
    void deleteFruit_returns404_whenFruitNotFound() {
        Long nonExistingId = 777L;

        assertThatNoException().isThrownBy(() ->
                mockMvc.perform(delete("/fruits/{id}", nonExistingId))
                        .andExpect(status().isNotFound())
        );
    }
}
