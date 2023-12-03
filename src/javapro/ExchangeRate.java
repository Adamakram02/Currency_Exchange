package javapro;

import java.util.Objects;

public class ExchangeRate {
    private currency cu1, cu2;
    private Double d1;


    public ExchangeRate(currency cu1, currency cu2, Double d1) {
        this.cu1 = cu1;
        this.cu2 = cu2;
        this.d1 = d1;
    }

    public currency getCu1() {
        return cu1;
    }

    public currency getCu2() {
        return cu2;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

    @Override
    public String toString() {
        return String.format("1%s=%s%s",cu1,d1,cu2);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(cu1, that.cu1) && Objects.equals(cu2, that.cu2) ;
    }


}
