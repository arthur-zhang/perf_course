cpu_05_perf:
	perf record -F 99 -p `pidof cpu_05` -g -- sleep 30
	perf script > perf.unfold
	FlameGraph/stackcollapse-perf.pl perf.unfold > perf.folded
	FlameGraph/flamegraph.pl perf.folded > perf.svg

cpu_06_offcpu:
	gcc cpu_06_offcpu.c -std=c99 -lpthread -o cpu_06_offcpu
cpu_06_perf:
	offcputime-bpfcc -df  -p `pidof cpu_06_offcpu` 30 > off.folded
	FlameGraph/flamegraph.pl --color=io ./off.folded > offcpu.svg

context_switch_test:
	gcc -std=c99 context_switch_test.c -lpthread -o context_switch_test