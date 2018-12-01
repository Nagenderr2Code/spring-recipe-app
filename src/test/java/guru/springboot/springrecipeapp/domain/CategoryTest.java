package guru.springboot.springrecipeapp.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import static org.junit.Assert.assertEquals;

@Slf4j
public class CategoryTest {

    Category category;

    @BeforeClass
    public static void beforeClass() {
        log.info("Executing Before Class..");
    }

    @AfterClass
    public static void afterClass() {
        log.info("Executing After Class..");
    }

    @Before
    public void setUp() throws Exception {
        log.info("Junit Setup for categotyTest");
        category = new Category();
    }

    @After
    public void tearDown() throws Exception {
        log.info("Junit tearDown for categotyTest");
    }

    @Test
    public void getId() {
        Long idValue = 4l;
        category.setId(idValue);
        log.info("Junit getId() for categotyTest");
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}