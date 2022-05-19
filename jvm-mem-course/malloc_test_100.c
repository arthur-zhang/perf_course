
#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>

#define _1_G (1 * 1024 * 1024 * 1024)

int main() {
//    char *p = NULL;
//    if ((p = mmap(NULL, _1_G, PROT_NONE, MAP_ANON | MAP_PRIVATE, -1, 0)) != MAP_FAILED) {
//        printf("p: %p", p);
//    }
//    getchar();
//    mprotect(p, _1_G/2, PROT_READ | PROT_WRITE);
//    if ((p = mmap(NULL, _1_G, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, -1, 0)) != MAP_FAILED) {
//        printf("p: %p", p);
//    }
//    char* p = malloc(_1_G);
//    printf("p: %p", p);
    char *p = NULL;
    p = mmap(NULL, _1_G, PROT_NONE, MAP_NORESERVE | MAP_ANONYMOUS | MAP_PRIVATE, -1, 0);
    printf("map done,p: %p", p);

    getchar();

    char *p2 = mmap(p, _1_G / 2, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_FIXED | MAP_ANONYMOUS, -1, 0);
    printf("p: %p", p2);

    getchar();
}
