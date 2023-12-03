package javapro;

import java.util.Objects;

public class currency {
    private String name;
    private String R;
    public currency(String name,String R) {
        this.name = name;
        this.R =R;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)",name,R);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        currency currency = (currency) o;
        return Objects.equals(name, currency.name) ;
    }


}
