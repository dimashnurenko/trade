FROM openjdk:8
ADD /target/core-api-0.0.1-SNAPSHOT.jar trade.jar
EXPOSE 8081
ADD wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN bash -c 'touch /trade.jar'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]
