export enum ScanTiming {
    T0 = "T0",
    T1 = "T1",
    T2 = "T2",
    T3 = "T3",
    T4 = "T4",
    T5 = "T5",
}

export const scanTimingLabels: Record<ScanTiming, { label: string; description: string }> = {
    T0: {
      label: "Paranoid",
      description: "Velmi pomalý, pro nenápadné skeny. Trvá i hodiny.",
    },
    T1: {
      label: "Sneaky",
      description: "Pomalý, pro obcházení IDS. Desítky minut až hodiny.",
    },
    T2: {
      label: "Polite",
      description: "Šetří šířku pásma a cílové zařízení. 10× pomalejší než běžný.",
    },
    T3: {
      label: "Normal",
      description: "Výchozí vyvážený režim. Trvá několik minut.",
    },
    T4: {
      label: "Aggressive",
      description: "Rychlý sken pro stabilní sítě. Sekundy až minuty.",
    },
    T5: {
      label: "Insane",
      description: "Extrémně rychlý, ale může být méně přesný.",
    },
  };