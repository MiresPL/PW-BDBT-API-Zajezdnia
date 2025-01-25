package com.mires.bdbt.zajezdnia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KierowcaAdresWrapper {
    private Kierowca kierowca;
    private Adresy adresy;
}
