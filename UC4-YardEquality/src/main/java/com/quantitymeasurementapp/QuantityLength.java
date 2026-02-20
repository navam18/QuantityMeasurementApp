import java.util.Objects;

class QuantityLength {
    double value;
    LengthUnit unit;

    QuantityLength(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    double convertToFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.convertToFeet(), other.convertToFeet()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToFeet());
    }
}