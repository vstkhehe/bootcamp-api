FROM public.ecr.aws/amazoncorretto/amazoncorretto:11
EXPOSE 80
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} course.jar
ENTRYPOINT java -jar -Dspring.profiles.active=prd course.jar
