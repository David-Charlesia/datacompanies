import json

def get():
    value = (str(data_dict[key]))
    value = value.replace(" ","_")
    value = value.replace(".","")
    value = value.replace(",","")
    value = value.replace("(","")
    value = value.replace(")","")
    value = value.replace("?","")
    value = value.replace(":","")
    value = value.replace(";","")
    value = value.replace("&","")
    value = value.replace("+","")

    value = value.replace("__","_")
    value = value.replace("___","_")
    value = value.replace("____","_")
    if(value[0]=="_"):
        value = value[1:]
    
    if(value[-1]=="_"):
        value = value[:-1]
    value = value.replace("__","_")
        
    value = value.upper()

    return value

path = "./src/main/java/org/lpld/datacompanies/backend/Mapping_Code_C.json"

with open(path) as json_data:
    data_dict = (json.load(json_data))[0]
    
    fichier = open("data.txt", "w")

    for key in data_dict:
        #print(key+data_dict[key])

        value = get()
        key = key.upper()

        fichier.write("\n")
        fichier.write("\n public static final String "+value+"_STRING = \""+value+"\";")
        #fichier.write("\n public int "+key+" = \""+value+"\";")
        #fichier.write("public Object get"+key+"(){ return ")

    fichier.close()

    fichier = open("data2.txt", "w")
    for key in data_dict:
        value = get()
        key = key.lower()
        #fichier.write("\nthis."+value+" = doc.get(\""+key+"\");")
        fichier.write("\nif(doc.get(\""+key+"\")!=null){")
        fichier.write("\n   this.set"+value+"((int)doc.get(\""+key+"\"));")
        fichier.write("\n}\n")
    fichier.close

    fichier = open("data3.txt", "w")
    for key in data_dict:
        value = get()
        key = key.lower()
        fichier.write("\ndict.put(\""+key+"\",List.of("+value+"_STRING));")
        fichier.write("\ndict.put("+value+"_STRING,List.of(\""+key+"\"));")

    fichier = open("data4.txt", "w")
    for key in data_dict:
        value = get()
        fichier.write("\ncase "+value+"_STRING:")
        fichier.write("\n   this.set"+value+"(Integer.parseInt(value));")
        fichier.write("\n   break;\n")





