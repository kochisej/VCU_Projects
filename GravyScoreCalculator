genetic_code = {'CG': '', 'TTT': 'F', 'TCT': 'S', 'TAT': 'Y', 'TGT': 'C', 'TTC': 'F', 'TCC': 'S', 'TAC': 'Y', 'TGC': 'C', 'TTA': 'L',
			    'TCA': 'S', 'TAA': '*', 'TGA': '*', 'TTG': 'L', 'TCG': 'S', 'TAG': '*', 'TGG': 'W', 'CTT': 'L', 'CCT': 'P', 'CAT': 'H', 
			    'CGT': 'R', 'CTC': 'L', 'CCC': 'P', 'CAC': 'H', 'CGC': 'R', 'CTA': 'L', 'CCA': 'P', 'CAA': 'Q', 'CGA': 'R', 'CTG': 'L', 
			    'CCG': 'P', 'CAG': 'Q', 'CGG': 'R', 'ATT': 'I', 'ACT': 'T', 'AAT': 'N', 'AGT': 'S', 'ATC': 'I', 'ACC': 'T', 'AAC': 'N', 
			    'AGC': 'S', 'ATA': 'I', 'ACA': 'T', 'AAA': 'K', 'AGA': 'R', 'ATG': 'M', 'ACG': 'T', 'AAG': 'K', 'AGG': 'R', 'GTT': 'V', 
			    'GCT': 'A', 'GAT': 'D', 'GGT': 'G', 'GTC': 'V', 'GCC': 'A', 'GAC': 'D', 'GGC': 'G', 'GTA': 'V', 'GCA': 'A', 'GAA': 'E', 
			    'GGA': 'G', 'GTG': 'V', 'GCG': 'A', 'GAG': 'E', 'GGG': 'G'} #sets up the dictionary for the genetic code about to be inspected  
                              
fasta_file = open('seq.fasta')#we will open pur modified file
d_list = fasta_file.read() #will read at most size bytes from file 
d_list_2 = ''.join(c for c in d_list if c not in "[], '" ) #creates list for size bytes from file given they are not present in the data set 

result = [] # creates empty list to be appended with info and data 

for i in range(0, len(d_list_2), 3): #sets up condition for iteration given range of length as determined 
	codon = d_list_2[i:i+3] #creates codon from range set up earlier 
	#print codon #prints found codon into list 
	if codon == 'TAA': #sets condition to see if codon is equal to this specified one 
		result.append(genetic_code[codon]) #if codons are equal it will be appeneded to list genetic_code
	#	break #terminated loop and resumes execution at next statement 
	elif codon =='TAG': #sets condition to see if codon is equal to this specified one
		result.append(genetic_code[codon]) #if codons are equal it will be appeneded to list 
	#	break #terminated loop and resumes execution at next statement 
	elif codon =='TGA': #sets condition to see if codon is equal to this specified one
		result.append(genetic_code[codon]) #if codons are equal it will be appeneded to list 
	#	break #terminated loop and resumes execution at next statement 
	else: #otherwise if those conditions are not met 
		result.append(genetic_code[codon]) # appeneded codons to specified list

protein = ''.join(result) #creates variable and returns string elements separated by str separator
#print protein

p = re.compile(ur'M(.*?)\*', re.MULTILINE) # seperates groups of protein sequences by detecting start and * at each point in the string
prot_filt = re.findall(p, protein) #uses regulat ecpression to locate all sequences of proteins as determined by p
prot_filt_2 = [s for s in prot_filt if not s == ''] #if line is not equal to that of the next it is replaced with space, filters all unequal lines
#print prot_filt_2
prot_filt_3 = ["M" + prot for prot in prot_filt_2] #appends an M amino acid to begining of the list created by last variable
#print prot_filt_3

gravy_score = {'A': 1.800, 'R': -4.500, 'N': -3.500, 'D': -3.500, 'C': 2.500, 'Q': -3.500, 'E': -3.500, 'G': -0.400,
			   'H': -3.200, 'I': 4.500, 'L': 3.800, 'K': -3.900, 'M': 1.900, 'F': 2.800, 'P': -1.600, 'S': -0.800, 'T': -0.700,
			   'W': -0.900, 'Y': -1.300, 'V': 4.200} #creates dictionary to be used in calculation of gravy scores 

weight_list = [] # empty list created to store weight information 
for na in prot_filt_3: # sets up condition for an iteration in the variable from above
    weight = sum(gravy_score[a] for a in na) #sums the replaced codes to solve for weight 
    weight_list.append((weight)) #appends weight of equated protein into empty list created above 
weight_amino_acids = list(enumerate(weight_list)) # turns weights into enumerated objects in list 

test_len = [len(i) for i in prot_filt_3] #looks at lengths for each iteration of sequence in the aforementioned variable
#print test_len
#print weight_list

gravy_score = [float(b) / float(m) for b,m in zip(weight_list, test_len)] # calculates gravy score by dividing weight by length as determined from the above lists/variables (float numbers)
gravy_score_2 = [ '%.5f' % elem for elem in gravy_score] #this function goes through the newly created gravy score list and re-creates each answer to five decimal places
print gravy_score_2 #prints gravy score

#Writes the output and sequence ID to a file 
h = open('seq_id.txt', 'r')
seq_id = h.readlines()
seq_id_2 = ''.join(c for c in seq_id if c not in '"[], "' )
words = seq_id_2.split(";")

for c1, c2, c3 in zip(words, test_len, gravy_score_2):
	f4.write("%s %s %s\n" % (c1, c2, c3))
h.close()
f4.close()
