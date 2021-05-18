FROM python:3.8 

COPY . /gadogado

WORKDIR /gadogado

RUN pip3 install -r requirements.txt

ENTRYPOINT ["python3"]

CMD ["api.py"]