package com.wsbp.scan_service.scanUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flags {
    private List<String> flags = new ArrayList<>();

    public List<String> getFlags() {
        List<String> list = new ArrayList<>();
        for (String flag:
             this.flags) {
            list.add("-" + flag);
        }
        return list;
    }

    public void addFlag(String flag) {
        this.flags.add(flag);
    }
}
