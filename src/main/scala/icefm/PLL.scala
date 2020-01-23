package icefm

import spinal.core._

// Top level component. Sends PLL output to pin f, and PLL lock state to D5 (green LED)
class PLLSoc extends Component {
  val io = new Bundle {
    val f = out Bool
    val D5 = out Bool
  }

  // Omit io_ pin prefix
  noIoPrefix()

  val pll = new PLL()
  val pll_clock = Reg(Bool)

  // An example of reset logic, not that it matters much in this example.
  when(clockDomain.isResetActive) {
    pll_clock := False
  } otherwise {
    // The normal case: flip the clock back and forth.
    pll_clock := !pll_clock
  }

  // Drive the PLL with the pll_clock register
  pll.io.clock_in := pll_clock

  // Wire the PLL output to pins f and D5 (see stick.pcf for pin mappings)
  io.f := pll.io.clock_out
  io.D5 := pll.io.locked
}

// BlackBox wrapper for icepll output. See gen-pll.sh and icepll docs.
class PLL extends BlackBox {
  val io = new Bundle {
    val clock_in = in Bool
    val clock_out = out Bool
    val locked = out Bool
  }
  noIoPrefix()
  addRTLPath("./PLL.v")
}

// Top level synthesis target. Run this to generate PLLSoc.v
object PLLVerilog {
  def main(args: Array[String]) {
    SpinalVerilog(new PLLSoc)
  }
}
