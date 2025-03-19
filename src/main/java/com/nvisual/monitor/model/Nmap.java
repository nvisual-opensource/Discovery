package com.nvisual.monitor.model;
public class Nmap {
    private String instructions;
    private String description;

    // 构造函数
    public Nmap(String instructions, String description) {
        this.instructions = instructions;
        this.description = description;
    }

    // Getter 和 Setter 方法
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nmap [instructions=" + instructions + ", description=" + description + "]";
    }
}
