#!/bin/bash

# Local Testing Script for Øving 1
# This script allows students to test their solutions locally before pushing to GitHub

#set -e  # Exit on error

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TESTDATA_DIR="${SCRIPT_DIR}/testdata"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo "================================================"
echo "  Local Testing Script - Øving 1"
echo "================================================"
echo ""

# Check if testdata exists
if [ ! -d "$TESTDATA_DIR" ]; then
    echo -e "${RED}Error: testdata directory not found!${NC}"
    echo "Please download the test data from the course repository."
    exit 1
fi

PASSED=0
FAILED=0

# Function to run a test
run_test() {
    local test_name=$1
    local command=$2
    
    echo -n "Testing: $test_name... "
    
    if eval "$command" > /dev/null 2>&1; then
        echo -e "${GREEN}✅ PASSED${NC}"
        ((PASSED++))
    else
        echo -e "${RED}❌ FAILED${NC}"
        ((FAILED++))
    fi
}

# ============================================================
# OPPGAVE 1: CSV Parsing
# ============================================================

echo ""
echo "--- Oppgave 1: CSV Parsing ---"

if [ -d "oppgave1" ]; then
    cd oppgave1
    
    # Compile
    echo -n "Compiling LesStudenter.java... "
    if javac LesStudenter.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Test with correct CSV
        run_test "Correct CSV" \
            "java LesStudenter ${TESTDATA_DIR}/data/studenter_korrekt.csv | diff -q ${TESTDATA_DIR}/expected/oppgave1_korrekt_output.txt -"
        
        # Test with empty CSV
        run_test "Empty CSV" \
            "java LesStudenter ${TESTDATA_DIR}/data/studenter_tom.csv"
        
        # Test with malformed CSV
        run_test "Malformed CSV" \
            "java LesStudenter ${TESTDATA_DIR}/data/studenter_feilformat.csv | diff -q ${TESTDATA_DIR}/expected/oppgave1_feilformat_output.txt -"
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED+=3))
    fi
    
    cd ..
else
    echo -e "${YELLOW}⚠️  Oppgave 1 directory not found${NC}"
fi

# ============================================================
# OPPGAVE 2: Linear Search
# ============================================================

echo ""
echo "--- Oppgave 2: Linear Search ---"

if [ -d "oppgave2" ]; then
    cd oppgave2
    
    # Compile DataGenerator
    echo -n "Compiling DataGenerator.java... "
    if javac DataGenerator.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Test generator
        run_test "DataGenerator (1000 lines)" \
            "java DataGenerator test.csv 1000 && [ \$(wc -l < test.csv) -eq 1000 ]"
        
        rm -f test.csv
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED++))
    fi
    
    # Compile FinnBruker
    echo -n "Compiling FinnBruker.java... "
    if javac FinnBruker.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Test search
        run_test "Search for bruker5" \
            "java FinnBruker ${TESTDATA_DIR}/data/brukere_10k.csv bruker5@epost.no | grep -q 'bruker5@epost.no'"
        
        run_test "Search for bruker9999" \
            "java FinnBruker ${TESTDATA_DIR}/data/brukere_10k.csv bruker9999@epost.no | grep -q 'bruker9999@epost.no'"
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED+=2))
    fi
    
    cd ..
else
    echo -e "${YELLOW}⚠️  Oppgave 2 directory not found${NC}"
fi

# ============================================================
# OPPGAVE 3: Hash Join
# ============================================================

echo ""
echo "--- Oppgave 3: Hash Join ---"

if [ -d "oppgave3" ]; then
    cd oppgave3
    
    # Compile
    echo -n "Compiling HashJoin.java... "
    if javac HashJoin.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Test join
        run_test "Hash Join" \
            "java HashJoin ${TESTDATA_DIR}/data/studenter.csv ${TESTDATA_DIR}/data/kurs.csv ${TESTDATA_DIR}/data/paameldinger.csv | sort | diff -q <(sort ${TESTDATA_DIR}/expected/oppgave3_output.txt) -"
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED++))
    fi
    
    cd ..
else
    echo -e "${YELLOW}⚠️  Oppgave 3 directory not found${NC}"
fi

# ============================================================
# OPPGAVE 4: Indexing
# ============================================================

echo ""
echo "--- Oppgave 4: Indexing ---"

if [ -d "oppgave4" ]; then
    cd oppgave4
    
    # Compile IndeksBygger
    echo -n "Compiling IndeksBygger.java... "
    if javac IndeksBygger.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Build index
        run_test "Build index" \
            "java IndeksBygger ${TESTDATA_DIR}/data/brukere_10k.csv brukere.idx && [ -f brukere.idx ]"
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED++))
    fi
    
    # Compile SokMedIndeks
    echo -n "Compiling SokMedIndeks.java... "
    if javac SokMedIndeks.java 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
        
        # Test indexed search
        run_test "Indexed search" \
            "java SokMedIndeks ${TESTDATA_DIR}/data/brukere_10k.csv brukere.idx bruker9999@epost.no | grep -q 'bruker9999@epost.no'"
    else
        echo -e "${RED}❌ Compilation failed${NC}"
        ((FAILED++))
    fi
    
    rm -f brukere.idx
    cd ..
else
    echo -e "${YELLOW}⚠️  Oppgave 4 directory not found${NC}"
fi

# ============================================================
# Summary
# ============================================================

echo ""
echo "================================================"
echo "  Test Summary"
echo "================================================"
echo -e "Passed: ${GREEN}${PASSED}${NC}"
echo -e "Failed: ${RED}${FAILED}${NC}"
echo ""

if [ $FAILED -eq 0 ]; then
    echo -e "${GREEN}🎉 All tests passed! Your code is ready to submit.${NC}"
    exit 0
else
    echo -e "${RED}⚠️  Some tests failed. Please review your code.${NC}"
    exit 1
fi
