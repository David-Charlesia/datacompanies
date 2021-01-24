import json

path = "./src/main/java/org/lpld/datacompanies/backend/Mapping_Code_C.json"

with open(path) as json_data:
    data_dict = (json.load(json_data))[0]
    
    fichier = open("data.txt", "w")

    for key in data_dict:
        #print(key+data_dict[key])

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
        
        value = value.replace("__","_")
        
        value = value.upper()
        key = key.upper()

        fichier.write("\n")
        fichier.write("\n public static final String "+value+";")
        #fichier.write("\n public int "+key+" = \""+value+"\";")
        #fichier.write("public Object get"+key+"(){ return ")

    fichier.close()