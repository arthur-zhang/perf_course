#include <stdio.h>
#include <sys/mman.h>

#define S_1G (1 * 1024 * 1024 * 1024)

int main() {
    char *p1 = mmap(NULL, S_1G, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, -1, 0);
    printf("p1: %p\n", p1);
    getchar();
    char *p2 = mmap(NULL, S_1G, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, -1, 0);
    printf("p2: %p\n", p2);
    getchar();
    printf("write p1\n");
    mprotect(p1 + S_1G / 2, 4096, PROT_NONE);
    printf("write done\n");
    getchar();
}