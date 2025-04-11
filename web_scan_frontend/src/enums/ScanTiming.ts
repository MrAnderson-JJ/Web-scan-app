export enum ScanTiming {
  T0 = "T0",
  T1 = "T1",
  T2 = "T2",
  T3 = "T3",
  T4 = "T4",
  T5 = "T5",
}

export const scanTimingLabels: Record<
  ScanTiming,
  { label: string; description: string }
> = {
  T0: {
    label: "Paranoid",
    description: "Very slow, used for stealthy scans. Can take hours.",
  },
  T1: {
    label: "Sneaky",
    description:
      "Slow, intended to bypass IDS. Takes tens of minutes to hours.",
  },
  T2: {
    label: "Polite",
    description:
      "Conserves bandwidth and target resources. 10× slower than normal.",
  },
  T3: {
    label: "Normal",
    description: "Default balanced mode. Takes a few minutes.",
  },
  T4: {
    label: "Aggressive",
    description: "Fast scan for stable networks. Seconds to minutes.",
  },
  T5: {
    label: "Insane",
    description: "Extremely fast, but may be less accurate.",
  },
};
