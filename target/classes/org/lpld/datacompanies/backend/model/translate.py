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


code_file = open("./src/main/java/org/lpld/datacompanies/backend/model/code.txt","r")
lines = code_file.readlines()

data5 = open("data5.txt", "w")
for line in lines:
    start = 22
    pos = 23
    while(line[pos]!="\""):
        pos+=1

    if(pos-start<15):
        a = line[start:pos].upper()
        data5.write("\n"+line[:start]+a+line[pos:])
        print(line[:22]+a+line[pos:])
    else:
        start = line.find("List.of(")+9
        pos = start
        while(line[pos]!="\""):
            pos+=1
        data5.write("\n"+line[:start]+a+line[pos:])
        print("\n"+line[:start]+a+line[pos:])

data5.close()





