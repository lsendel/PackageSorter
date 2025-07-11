package com.thoughtful;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PackageSorterTest {
    
    private PackageSorter sorter;
    
    @BeforeEach
    void setUp() {
        sorter = new PackageSorter();
    }
    
    @Test
    void testStandardPackages() {
        // Small packages should be STANDARD
        assertThat(sorter.sort(10, 10, 10, 5)).isEqualTo("STANDARD");
        assertThat(sorter.sort(50, 50, 50, 5)).isEqualTo("STANDARD");
        assertThat(sorter.sort(30, 40, 50, 10)).isEqualTo("STANDARD");
    }
    
    @Test
    void testBulkyPackages() {
        // Large dimension makes it SPECIAL
        assertThat(sorter.sort(150, 10, 10, 5)).isEqualTo("SPECIAL");
        assertThat(sorter.sort(10, 150, 10, 5)).isEqualTo("SPECIAL");
        assertThat(sorter.sort(10, 10, 150, 5)).isEqualTo("SPECIAL");
        
        // Large volume makes it SPECIAL
        assertThat(sorter.sort(100, 100, 100, 5)).isEqualTo("SPECIAL");
    }
    
    @Test
    void testHeavyPackages() {
        // Heavy packages are SPECIAL
        assertThat(sorter.sort(10, 10, 10, 20)).isEqualTo("SPECIAL");
        assertThat(sorter.sort(50, 50, 50, 25)).isEqualTo("SPECIAL");
    }
    
    @Test
    void testRejectedPackages() {
        // Both bulky and heavy means REJECTED
        assertThat(sorter.sort(150, 150, 150, 20)).isEqualTo("REJECTED");
        assertThat(sorter.sort(100, 100, 100, 20)).isEqualTo("REJECTED");
        assertThat(sorter.sort(200, 50, 50, 25)).isEqualTo("REJECTED");
    }
    
    @Test
    void testBoundaryValues() {
        // Just under the limits
        assertThat(sorter.sort(149.9, 10, 10, 19.9)).isEqualTo("STANDARD");
        
        // Exactly at the limits
        assertThat(sorter.sort(150, 10, 10, 10)).isEqualTo("SPECIAL");
        assertThat(sorter.sort(10, 10, 10, 20)).isEqualTo("SPECIAL");
        
        // Just over the limits
        assertThat(sorter.sort(150.1, 10, 10, 10)).isEqualTo("SPECIAL");
        assertThat(sorter.sort(10, 10, 10, 20.1)).isEqualTo("SPECIAL");
    }
    
    @Test
    void testInvalidInputs() {
        // Negative values should throw exception
        assertThatThrownBy(() -> sorter.sort(-1, 10, 10, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("width must be positive");
            
        // Zero values should throw exception
        assertThatThrownBy(() -> sorter.sort(0, 10, 10, 5))
            .isInstanceOf(IllegalArgumentException.class);
            
        // NaN should throw exception
        assertThatThrownBy(() -> sorter.sort(Double.NaN, 10, 10, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("cannot be NaN");
    }
    
    @Test
    void testVolumeCalculation() {
        // Test that volume threshold works correctly
        assertThat(sorter.sort(99.99, 100, 100, 5)).isEqualTo("STANDARD");  // Just under 1M
        assertThat(sorter.sort(100, 100, 100, 5)).isEqualTo("SPECIAL");     // Exactly 1M
        assertThat(sorter.sort(50, 200, 100, 5)).isEqualTo("SPECIAL");      // Also 1M
    }
}