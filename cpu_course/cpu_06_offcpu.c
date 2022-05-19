#include <unistd.h>
#include <sys/epoll.h>

#define MAX_EVENT 10

int main() {
    int ep_fd;
    ep_fd = epoll_create(MAX_EVENT);
    struct epoll_event events[MAX_EVENT];
    int ready;
    while (1) {
        ready = epoll_wait(ep_fd, events, MAX_EVENT, 20); // 阻塞 20ms，超时后返回
        usleep(30 * 1000); // 睡眠 30ms ，超时后返回
    }
    close(ep_fd);
    return 0;
}