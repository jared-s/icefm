// Generator : SpinalHDL v1.3.8    git head : 57d97088b91271a094cebad32ed86479199955df
// Date      : 22/01/2020, 19:49:20
// Component : PLLSoc


module PLLSoc (
      output  f,
      output  D5,
      input   reset,
      input   clk);
  wire  pll_1__clock_out;
  wire  pll_1__locked;
  reg  pll_clock;
  PLL pll_1_ ( 
    .clock_in(pll_clock),
    .clock_out(pll_1__clock_out),
    .locked(pll_1__locked) 
  );
  assign f = pll_1__clock_out;
  assign D5 = pll_1__locked;
  always @ (posedge clk) begin
    if(reset)begin
      pll_clock <= 1'b0;
    end else begin
      pll_clock <= (! pll_clock);
    end
  end

endmodule

