package com.example.fleet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(GPSController.class)
// Bypass JWT Unit Test
@org.springframework.test.context.ActiveProfiles("test")
public class GPSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleRepository vehicleRepo;

    @MockBean
    private GPSLogRepository gpsLogRepo;

    @Test
    void testAddGPS_Valid() throws Exception {
        GPSLogDTO dto = new GPSLogDTO();
        dto.setVehicleId(1L);
        dto.setLatitude(-6.2);
        dto.setLongitude(106.8);
        dto.setSpeed(80);
        dto.setTimestamp(LocalDateTime.now());

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setPlateNumber("B1234XYZ");
        vehicle.setName("Truk 1");
        vehicle.setType("Truck");

        when(vehicleRepo.findById(1L)).thenReturn(Optional.of(vehicle));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/api/gps")
                .header("Authorization", "Bearer dummy-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}