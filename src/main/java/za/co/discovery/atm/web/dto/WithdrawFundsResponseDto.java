package za.co.discovery.atm.web.dto;

import za.co.discovery.atm.domain.Denomination;


public class WithdrawFundsResponseDto {
    private Denomination denomination;
    private int count;

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
