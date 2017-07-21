import subprocess as sp
import sys,os,time,re
from collections import defaultdict
# Usage for blast.py: python blast.py myseq.fasta nucl/prot
# make a blast database from fastafile

class Blast(object):
    def __init__(self,fasta_in,dbt,query,out = 'blastout.txt'):
        self.subject = fasta_in
        self.dbtype = dbt
        self.query = query
        self.outfile = out

    def makedb(self,force = False):
        """Create a custom database from a multi-FASTA file of sequences with this minimal command:
        makeblastdb -in mydb.fsa dbtype nucl -parse_seqids  
        subprocess.Popen requires a list of arguments"""
        print "Creating subject database..."
        print  (os.path.isfile(self.subject + ".phr") and os.path.isfile(self.subject + ".pin") and os.path.isfile(self.subject + ".psq"))
        if not (os.path.isfile(self.subject + ".phr") and os.path.isfile(self.subject + ".pin") and os.path.isfile(self.subject + ".psq")) or force:
            sp.Popen(['/usr/local/ncbi-blast-2.2.29+/bin/makeblastdb','-in',self.subject,'-dbtype',self.dbtype]).wait()
        else:
            return "Database already exists continuing"
        return "Finished created blastdb"

    def blast(self):
        # invoke blastn or blastp to search against that database
        print "Running blast... please be patient"
        s = time.time()
        if self.dbtype == "nucl":
        #use blastn
        	sp.Popen(['/usr/local/ncbi-blast-2.2.29+/bin/blastn','-db',self.subject,'-query',self.query,'-outfmt','7 stitle qstart sstart qend send qcovs qseqid','-out',self.outfile]).wait()

        elif self.dbtype == "prot":
        #use blastp
        	sp.Popen(['/usr/local/ncbi-blast-2.2.29+/bin/blastp','-db',self.subject,'-query',self.query,'-outfmt','7 stitle qstart sstart qend send qcovs qseqid','-out',self.outfile]).wait()
        e = time.time()
        return "Done running blast in %0.6f seconds" % (e - s)

class BlastReport(object):
    
    def __init__(self,blastresfile = 'blastout.txt'):
        self.file = blastresfile
        self.results = []
        self.parse() #call parse as soon as we create the object so that we populate the results

    def parse(self):
        find_query = re.compile("#\sQuery:\s.*").search
        pos = -1
        for line in open(self.file):
            if find_query(line):
                query = re.sub("#\sQuery:\s","",line)
                result_dict = defaultdict(defaultdict)
                self.results.append(result_dict)
                pos += 1
            elif line.startswith("#"):
                continue
            else:
                line = line.split('\t')
                self.results[pos][query][line[0]] = {'query_coords': line[1] + ":" + line[3],
                                               'subject_coords' : line[2] + ":" + line[4],
                                               'percent_query_coverage' : line[5] }
        print self.results
    
    def qcovs(self):
        #this takes the data that was parsed from the blast report and returns the percent query coverage
        qcov_list = []
        for result in self.results:
            for query in result:
                for subject in result[query]:
                    qcov_list.append((query,subject,result[query][subject]['percent_query_coverage']))
        return qcov_list

#This is the Main Program that will run when you type python blast.py	
filelist = ['2000to2001.fasta','98to99.fasta','allto977.fasta']
#filelist = ['2000to2001.fasta','98to99.fasta','allto977.fasta']
#This is a file list in case you wanted to blast all of them in one loop which I did once
if __name__ == '__main__':
    for file in filelist:
        try:
            subject = 'allto97.fasta' #the fasta that will become the database
            dbt = 'prot'
            query = 'Query.fasta' #the fasta that will become the search
            outfile = 'test3.aln'
        except:
            print "Insufficient arguments provided, please try again."
            print "Usage: python blast.py subject.fasta nucl/prot query.fasta blastout.txt"
            exit(1)
        useblast = Blast(subject,dbt,query,outfile)
        print useblast.makedb()
        print useblast.blast()
        blast_results = BlastReport(outfile)
        for result in blast_results.qcovs():
            print "Query",result[0],"Aligned to Subject:",result[1],"at",result[2]+"%"
