package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.enums.Difficulity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class DifficulityConverterTest {


    DefaultConversionService service;

    @Before
    public void setUp() throws Exception {
        service = new DefaultConversionService();

        service.addConverter(new DifficulityConverter.StringToEnumConverter<>());
        service.addConverterFactory(new DifficulityConverter());
    }

    @Test
    public void getConverter() {
        assertThat(service.convert("MODERATE", Difficulity.class)).isEqualTo(Difficulity.MODERATE);
    }
}