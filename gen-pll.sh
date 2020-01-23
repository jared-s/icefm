#!env bash
# first argument is the target frequency in mhz. 
# achieved frequency may differ, watch the output.
icepll -o $1 -n PLL -m -f PLL.v
