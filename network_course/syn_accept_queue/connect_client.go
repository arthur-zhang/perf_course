package main

import (
	"fmt"
	"net"
	"time"
)

func main() {
	for i := 0; i < 2000; i++ {
		go connect()
	}
	time.Sleep(time.Minute * 10)
}
func connect() {
	_, err := net.Dial("tcp4", "192.168.31.172:8080")
	if err != nil {
		fmt.Println(err)
	}
	ch := make(chan int)
	_ = <- ch
}
