{{- define "payment-platform.name" -}}
payment-platform
{{- end -}}

{{- define "payment-platform.labels" -}}
app.kubernetes.io/name: {{ include "payment-platform.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}