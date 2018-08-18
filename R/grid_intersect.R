# http://members.chello.at/~easyfilter/bresenham.html

library(ggplot2)
library(dplyr)

quantize <- function(dx) {
  function(x) {
    round(x / dx) * dx
  }
}

circ <- function(r) {
  U <- data.frame(x=NULL,y=NULL)
  x <- -r
  y <- 0
  err <- 2- 2 * r
  while (x <= 0) {
    U <- rbind(U, data.frame(x = x, y = y))
    r <- err
    if (r <= y) {
        y <- y + 1
         err <- err + y * 2 + 1 # e_xy + e_y < 0
    }
    if (r > x || err > y) {
      x <- x + 1
      err <- err + x*2 + 1    # e_xy + e_x > 0 or no 2nd y-step
    }
  }
  return(U)
}

#UU <- circ(100)

scale <- 0.25
radius <- 1.0

t <- seq(0, 2*pi,length.out = 1024)

U <- data.frame(
  x = radius * cos(t),
  y = radius * sin(t),
  theta = t,
  color = "BLACK"
)

N <- round(radius / scale)
m <- (-N):N
xval <- m * scale
xtheta <- acos(xval / radius)
y1 <- radius * sin(xtheta)

yval <- m * scale
ytheta <- asin(yval / radius)
x1 <- radius * cos(ytheta)

U2 <- data.frame(
  x = c(xval, x1),
  y = c(y1, yval),
  theta = c(xtheta, ytheta),
  color = "RED"
)

U2 <- U2 %>% arrange(theta)

qq <- quantize(scale)
U2 <- U %>% mutate(qx=qq(x),qy=qq(y))

p <- ggplot(U2, aes(qx,qy)) + 
  geom_path(aes(color = color)) + geom_point()
print(p)

