from whoosh.index import create_in
from whoosh.fields import *
from whoosh.qparser import QueryParser
from typing import List
import os.path

# using default scoring BM25F and exact match
def search(patient: str, qry_str: str) -> List[str]:
    schema = Schema(patient=ID(stored=True), content=TEXT(stored=True))

    if not os.path.exists("index"):
        os.mkdir("index")
    ix = create_in("index", schema)

    patient = patient
    filename = "".join(["instructions\\", patient, ".txt"])

    # check if the patient instructions exists
    if not os.path.exists(filename):
        return []

    instruction = open(filename, 'r', encoding="utf8")

    writer = ix.writer()

    for line in instruction:
        line = line.strip()
        if line:
            writer.add_document(content=line, patient=patient)
    writer.commit()

    with ix.searcher() as searcher:
        query = QueryParser("content", ix.schema).parse(qry_str.replace(" ", " OR "))
        results = searcher.search(query)
        if len(results) == 0:
            return []
        else:
            return [result['content'] for result in results]