yosys -p "synth_ice40 -json pll.json" PLLSoc.v PLL.v
nextpnr-ice40 --json pll.json --freq 12 --asc pll.asc --pcf stick.pcf
icepack pll.asc pll.bin
