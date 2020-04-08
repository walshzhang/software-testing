package iteach.softwaretesting.tools.mockito;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class CarTest {
    private final Car ferrari = mock(Car.class);

    @Test
    public void testIfCarIsACar() {
        assumeTrue(ferrari != null);
        assertTrue(ferrari instanceof Car);
    }
}
