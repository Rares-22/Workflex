import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { Workation } from '@demo-ui/data-access-lib';
 import { WorkationService } from '@demo-ui/data-access-lib';


@Component({
  selector: 'lib-workations',
  imports: [CommonModule],
  templateUrl: './workations.component.html',
  styleUrl: './workations.component.scss',
})
export class WorkationsComponent implements OnInit {
  protected workations$!: Observable<Workation[]>;
  protected workations: Workation[] = [];
  protected highlightedRow!: Workation | null;

  protected riskIcons: { [key: string]: string } = {
    'HIGH': 'assets/red-risk.svg',
    'LOW': 'assets/yellow-risk.svg',
    'NO': 'assets/green-risk.svg',
  };

  constructor(private workationService: WorkationService) {}

  ngOnInit(): void {
    this.workations$ = this.workationService.getWorkations();
    this.workations$.subscribe((data) => {
      this.workations = data;
    });
  }

  protected sortTable(property: keyof Workation): void {
    this.workations.sort((a, b) => {
      if (a[property] < b[property]) {
        return -1;
      } else if (a[property] > b[property]) {
        return 1;
      } else {
        return 0;
      }
    });
  }
  

  protected highlightRow(workation: Workation): void {
    this.highlightedRow = workation;
  }

  protected removeHighlight(): void {
    this.highlightedRow = null;
  }

  protected getRiskClass(riskLevel: string): string {
    return riskLevel === 'LOW_RISK' ? 'low-risk' : 'no-risk';
  }

  protected getFlagImage(country: string): string {
    const flags: { [key: string]: string } = {
      'Germany': 'https://flagcdn.com/w320/de.png',
      'Greece': 'https://flagcdn.com/w320/gr.png',
      'India': 'https://flagcdn.com/w320/in.png',
      'Belgium': 'https://flagcdn.com/w320/be.png',
      'Spain': 'https://flagcdn.com/w320/es.png',
      'Ukraine': 'https://flagcdn.com/w320/ua.png',
      'United States': 'https://flagcdn.com/w320/us.png',
    };
    return flags[country] || '';
  }

  protected getRiskIcon(riskLevel: string): string {
    return this.riskIcons[riskLevel] || 'assets/green-risk.svg';
  }
}
