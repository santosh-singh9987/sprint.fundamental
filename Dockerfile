FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
LABEL authors="santoshsingh"

ENTRYPOINT ["top", "-b"]

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
