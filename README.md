# icefm - Proof of concept FM on the Lattice iCEstick

icefm is a basic proof of concept FM transmitter written in SpinalHDL and targetting the Lattice iCEstick. Any other Lattice FPGAs which have PLLs should be easy to support.

# Theory of Operation

The iCEstick has one PLL on board. The `icepll` program from the amazing icestorm software package can generate Verilog modules which instantiate the PLL.

SpinalHDL has a facility for importing arbitrary Verilog - the BlackBox component.

This project simply bridges these two technologies. The result is a routing of the PLL output frequency to a GPIO pin.

Example Verilog files are included which target 109.5MHz.

# Requirements

The scripts assume the following are present:

* sbt
* yosys
* nextpnr-ice40
* icepack

# Building


1. Generate `PLL.v`
    1. Run `gen-pll.sh` or otherwise run `icepll` to generate the PLL module with the expected name of `PLL.v`
1. Generate PLLSoc.v
    1. Load the project in IDEA and run the PLLVerilog target in PLL class.
    1. Alternatively, run `sbt "runMain icefm.PLLVerilog"`
1. Synthesize and route by running `synth.sh` or your process of choice.