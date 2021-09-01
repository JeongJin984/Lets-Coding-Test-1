package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func reverseWord(s string) (result string) {
	for _,v := range s {
		result = string(v) + result
	}
	return
}

func reverseLine(s string) (result string) {
	arr := strings.Split(s, " ")
	for _, v := range arr {
		result = result + reverseWord(v) + " "
	}
	return
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	i, _ := strconv.Atoi(scanner.Text())

	for i>0 {
		scanner.Scan()
		input := scanner.Text()
		result := reverseLine(input)
		fmt.Println(result)
		i--
	}
}
