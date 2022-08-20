FROM gradle:jdk11-alpine

# install bloop server
RUN apk update \
  && wget -O /usr/bin/coursier https://git.io/coursier-cli && chmod +x /usr/bin/coursier \
  && coursier install --dir /usr/bin bloop --only-prebuilt=true \
  && coursier bootstrap bloop --standalone -o bloop

USER root

VOLUME "/root/log"

WORKDIR /app

ADD --chown=gradle:gradle /bin/ .

RUN chmod -R +x *

RUN gradle bloopInstall

RUN bloop compile root

RUN bloop run root

CMD [""]
