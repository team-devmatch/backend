# project-infra

Docker / Kubernetes / CI/CD 인프라 설정

## 기술 스택
- Docker / Docker Compose
- Kubernetes (K8s)
- Helm (패키지 관리)
- GitHub Actions / Jenkins
- ArgoCD
- OpenShift (IBM Cloud)
- AWS (EC2, S3, RDS)
- Prometheus & Grafana (모니터링)

## 구성
- docker/ → Dockerfile 모음
- k8s/ → Kubernetes 설정 파일
- helm/ → Helm 차트
- .github/workflows/ → CI/CD 파이프라인

## 브랜치 규칙
- infra/작업명 → 인프라 설정
- main → 최종 배포 브랜치
