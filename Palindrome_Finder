gene_1 = open("genome.fasta", "r") #this function opens and reads the file labeled, "genome.fasta"
 
def complement_strand(seq): #this function takes a string and manipulates it as below
    complement_strand_dictionary = {'A': 'T', 'C': 'G', 'G': 'C', 'T': 'A'} #dictionary that will convert bases to their complement
    bases_list = list(seq) #takes the string and converts it to a list, dividing each nucleic acid as its own item in the list
    complement_bases = [complement_strand_dictionary[base] for base in bases_list] #
    return ''.join(complement_bases) #this joins the complement bases to form a strand
def reverse_complement_strand(seq): #this function defines the reverse complement strand
    return complement_strand(seq[::-1]) #this converts the complement strand into its reverse
 
orig_gene_seq = []#this creates an empty list called 'orig_gene_seq'
for line in gene_1: #this for loop goes through each string but calls it line in the previously opened file
    line = line.strip() #this command strips the sequence 
    if line.startswith ('>'): #this command helps filter out the genetic sequence's label
        continue #this command passes the line that starts with '>'
    orig_gene_seq.append(line) #this command adds the filtered genetic sequence to the 'orig_gene_seq' list
 
gene_seq_1 = "".join(orig_gene_seq) #this command joins all the nucleic acids in the orig_gene_seq list into one string
 
kmer_18 = [] #this list will contain the 18-mer of the regular DNA sequence
k = 18 #this is the number of kmer the for loop will divide the sequence into
for i in range(len(gene_seq_1) - k + 1): #this for loop goes through the sequence in an 18-mer fashion
    kmer = gene_seq_1[i:i+k] #this selects a range of the DNA sequence which is always 18 nucleic acids long, forming the kmers
    kmer_18.append(kmer) #this function adds all the strings of 18-mers into the kmer_18 list
 
palindromes = [] #this list will contain the 18-mers that are reverse complements of each other, thus palindromic
for kmer in kmer_18: #this foor loop selects each item in the kmer_18 and labels it as kmer
    if kmer == reverse_complement_strand(kmer): #this if command compares each kmer item to its reverse_complement to see if it is the same
        palindromes.append(kmer) #if the if statement above is met, then the kmer that is a reverse complement of itself will be appended to the 'palindrome' list
print "The reverse palindromes found in the genome.fasta file are:", ', '.join(palindromes) #this prints the reverse complement (palindromic) 18-kmers
