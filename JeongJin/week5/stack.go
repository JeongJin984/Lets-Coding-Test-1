package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
)

func main() {
	stack := list.New()

	bf := bufio.NewReader(os.Stdin)

	input := ""
	fmt.Fscanln(bf, &input)

	num, _ := strconv.Atoi(input)

	for num>0 {
		command := ""
		target := ""
		fmt.Fscanln(bf, &command, &target)

		switch command {
		case "push":
			intTarget, _ := strconv.Atoi(target)
			stack.PushBack(intTarget)
			break
		case "pop":
			if stack.Len() == 0 {
				fmt.Println(-1)
			} else {
				fmt.Println(stack.Back().Value)
				stack.Remove(stack.Back())
			}
			break
		case "size":
			fmt.Println(stack.Len())
			break
		case "empty":
			if stack.Len() == 0 {
				fmt.Println(1)
			} else {
				fmt.Println(0)
			}
			break
		case "top":
			if stack.Len() == 0 {
				fmt.Println(-1)
			} else {
				fmt.Println(stack.Back().Value)
			}
			break
		default:
			break
		}

		num--
	}
}
