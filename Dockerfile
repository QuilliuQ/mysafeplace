FROM openjdk:8-jdk
EXPOSE 103:8080
RUN mkdir /app
COPY ./build/install/mysafeplace/ /app/
WORKDIR /app/bin
CMD ["./mysafeplace"]