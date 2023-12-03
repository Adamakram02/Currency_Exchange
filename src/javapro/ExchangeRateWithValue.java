package javapro;

import java.util.Objects;

public class ExchangeRateWithValue {

private ExchangeRate ex;
private double x1;

    public ExchangeRateWithValue(ExchangeRate ex, double x1) {
        this.ex = ex;
         this.x1 = x1;
    }

    @Override
    public String toString() {
        return String.format("%s%s=%s%s",x1,ex.getCu1(),ex.getD1()*x1,ex.getCu2());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRateWithValue that = (ExchangeRateWithValue) o;
        return Double.compare(that.x1, x1) == 0 && Objects.equals(ex, that.ex);
    }


}
