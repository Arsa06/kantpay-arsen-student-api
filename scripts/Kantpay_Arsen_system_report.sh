#!/bin/bash
LOG_FILE="logs/Kantpay_Arsen_system_report.log"
STUDENT_FULL_NAME="Arsen Kantpay"
report() {
    echo "=========================================="
    echo " SYSTEM REPORT"
    echo " Student Name : $STUDENT_FULL_NAME"
    echo " Date & Time  : $(date '+%Y-%m-%d %H:%M:%S')"
    echo " Current User : $(whoami)"
    echo " OS & Kernel  : $(grep PRETTY_NAME /etc/os-release | cut -d= -f2 | tr -d '\"') ($(uname -r))"
    echo " CPU Model    : $(grep -m1 'model name' /proc/cpuinfo | cut -d: -f2 | xargs) ($(nproc) cores)"
    echo " Memory (RAM) :"
    free -h
    echo " Disk Usage   :"
    df -h /
    echo " Available Disk Space: $(df -h / | awk 'NR==2 {print $4}')"
    echo "=========================================="
}
report | tee -a "$LOG_FILE"
