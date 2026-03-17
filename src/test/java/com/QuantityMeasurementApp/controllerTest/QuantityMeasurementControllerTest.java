package com.QuantityMeasurementApp.controllerTest;

import com.QuantityMeasurementApp.DTO.QuantityDTO;
import com.QuantityMeasurementApp.service.IQuantityMeasurementService;
import com.QuantityMeasurementApp.controller.QuantityMeasurementController;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementControllerTest {

    @Test
    public void testController_DemonstrateEquality_Success(){

        IQuantityMeasurementService service = mock(IQuantityMeasurementService.class);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(2, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(24, "INCHES", "Length");

        when(service.compare(q1, q2)).thenReturn(true);

        boolean result = controller.performComparison(q1, q2);

        assertTrue(result);
    }

    @Test
    public void testController_DemonstrateAddition_Error(){

        IQuantityMeasurementService service = mock(IQuantityMeasurementService.class);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(100, "CELSIUS", "Temperature");
        QuantityDTO q2 = new QuantityDTO(50, "CELSIUS", "Temperature");

        when(service.add(q1, q2)).thenThrow(new RuntimeException("Not supported"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.performAddition(q1, q2);
        });

        assertTrue(exception.getMessage().contains("Not supported"));
    }
}